/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * Add your docs here.
 */
public class LimelightAPI extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  NetworkTable m_table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = m_table.getEntry("tx");
  NetworkTableEntry ty = m_table.getEntry("ty");
  NetworkTableEntry ta = m_table.getEntry("ta");



  /**
     * tv   Whether the limelight has any valid targets (0 or 1)
     * @return
     */
    public boolean tv_getIsTargetFound() {
      NetworkTableEntry tv = m_table.getEntry("tv");
      double v = tv.getDouble(0);
      if (v == 0.0f){
          return false;
      }else {
          return true;
      }
  }
/**
     * tx Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
     * @return
     */
    public double tx_getdegRotationToTarget() {
      NetworkTableEntry tx = m_table.getEntry("tx");
      double x = tx.getDouble(0.0);
      return x;
  }
  /**
   * ty Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
   * @return 
   */
  public double ty_getdegVerticalToTarget() {
      NetworkTableEntry ty = m_table.getEntry("ty");
      double y = ty.getDouble(0.0);
      return y;
  }
  /**
   * ta Target Area (0% of image to 100% of image)
   * @return
   */
  public double ta_getTargetArea() {
      NetworkTableEntry ta = m_table.getEntry("ta");
      double a = ta.getDouble(0.0);
      return a;
  }
  /**
   * ts Skew or rotation (-90 degrees to 0 degrees)
   * @return
   */
  public double ts_getSkew_Rotation() {
      NetworkTableEntry ts = m_table.getEntry("ts");
      double s = ts.getDouble(0.0);
      return s;
  }
  /**
   * tl The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency.
   * @return
   */
  public double tl_getPipelineLatency() {
      NetworkTableEntry tl = m_table.getEntry("tl");
      double l = tl.getDouble(0.0);
      return l;
  }

  private void resetPilelineLatency(){
      m_table.getEntry("tl").setValue(0.0);
  }
  
  public enum LedMode {
    kpipeLine(0),   //0	use the LED Mode set in the current pipeline
    kforceOff(1),   //1	force off
    kforceBlink(2), //2	force blink
    kforceOn(3);    //3	force on 

    private static final Map<Double, LedMode> MY_MAP = new HashMap<Double, LedMode>();
    
    static {
        for (LedMode LedMode : values()) {
        MY_MAP.put(LedMode.getValue(), LedMode);
        }
    }
    
    private double value;

    private LedMode(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static LedMode getByValue(double value) {
        return MY_MAP.get(value);
    }

    public String toString() {
        return name();
    }

}
  
  /**
   * LedMode  Sets limelight’s LED state
   * 
   *  kon
   *  koff
   *  kblink
   * @param ledMode
   */
   public void setLEDMode(LedMode ledMode) {
      m_table.getEntry("ledMode").setValue(ledMode.getValue());
  }

  /**
   * Returns current LED mode of the Lime Light
   * @return LedMode
   */
  public LedMode getLEDMode() {
      NetworkTableEntry ledMode = m_table.getEntry("ledMode");
      double led = ledMode.getDouble(0.0);
      LedMode mode = LedMode.getByValue(led);
      return mode;
  }

  public enum CamMode {
    kvision(0),
    kdriver(1);

    private static final Map<Double, CamMode> MY_MAP = new HashMap<Double, CamMode>();

    static {
        for (CamMode CamMode : values()) {
        MY_MAP.put(CamMode.getValue(), CamMode);
        }
    }
    
    private double value;

    private CamMode(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static CamMode getByValue(double value) {
        return MY_MAP.get(value);
    }

    public String toString() {
        return name();
    }
}
  
  /**
   * camMode  Sets limelight’s operation mode
   * 
   *  kvision
   *  kdriver (Increases exposure, disables vision processing)
   * @param camMode
   */
  
  public void setCamMode(CamMode camMode) {
      m_table.getEntry("camMode").setValue(camMode.getValue());
  }

  /**
   * Returns current Cam mode of the Lime Light
   * @return CamMode
   */
  public CamMode getCamMode() {
      NetworkTableEntry camMode = m_table.getEntry("camMode");
      double cam = camMode.getDouble(0.0);
      CamMode mode = CamMode.getByValue(cam);
      return mode;
  }

  /**
     * pipeline Sets limelight’s current pipeline
     * 
     * 0 . 9	Select pipeline 0.9
     * 
     * @param pipeline
     */
    public void setPipeline(Integer pipeline) {
      if(pipeline<0){
          pipeline = 0;
          throw new IllegalArgumentException("Pipeline can not be less than zero");
      }else if(pipeline>9){
          pipeline = 9;
          throw new IllegalArgumentException("Pipeline can not be greater than nine");
      }
      m_table.getEntry("pipeline").setValue(pipeline);
  }

  /**
   * Returns current Pipeling of the Lime Light
   * @return Pipelinge
   */
  public double getPipeline(){
      NetworkTableEntry pipeline = m_table.getEntry("pipeline");
      double pipe = pipeline.getDouble(0.0);
      return pipe;
  }

  /**
   * Returns current Pipeling of the Lime Light
   * @return Pipelinge
   */
  public Integer getPipelineInt(){
      NetworkTableEntry pipeline = m_table.getEntry("pipeline");
      Integer pipe = (int) pipeline.getDouble(0.0);
      return pipe;
  }

  public enum StreamType {
    kStandard(0),
    kPiPMain(1),
    kPiPSecondary(2);

    private static final Map<Double,  StreamType> MY_MAP = new HashMap<Double,  StreamType>();
    
    static {
        for ( StreamType  StreamType : values()) {
        MY_MAP.put( StreamType.getValue(),  StreamType);
        }
    }
    
    private double value;

    private  StreamType(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static  StreamType getByValue(double value) {
        return MY_MAP.get(value);
    }

    public String toString() {
        return name();
    }

}
  /**
   * stream   Sets limelight’s streaming mode
   * 
   * kStandard - Side-by-side streams if a webcam is attached to Limelight
   * kPiPMain - The secondary camera stream is placed in the lower-right corner of the primary camera stream
   * kPiPSecondary - The primary camera stream is placed in the lower-right corner of the secondary camera stream
   * 
   * @param stream
   */ 
  public void setStream(StreamType stream) {
      m_table.getEntry("stream").setValue(stream.getValue());
  }

  public StreamType getStream() {
      NetworkTableEntry stream = m_table.getEntry("stream");
      double st = stream.getDouble(0.0);
      StreamType mode = StreamType.getByValue(st);
      return mode;
  }

  public enum  Snapshot {

    kon(1), koff(0);
  
    private static final Map<Double,  Snapshot> MY_MAP = new HashMap<Double,  Snapshot>();
    
    static {
      for ( Snapshot  Snapshot : values()) {
        MY_MAP.put( Snapshot.getValue(),  Snapshot);
      }
    }
    
    private double value;
  
    private  Snapshot(double value) {
      this.value = value;
    }
  
    public double getValue() {
      return value;
    }
  
    public static  Snapshot getByValue(double value) {
      return MY_MAP.get(value);
    }
  
    public String toString() {
      return name();
    }
  
  }

  /**
   * snapshot Allows users to take snapshots during a match
   * 
   * kon - Stop taking snapshots
   * koff - Take two snapshots per second
   * @param snapshot
   */
  public void setSnapshot(Snapshot snapshot) {
      m_table.getEntry("snapshot").setValue(snapshot.getValue());
  }

  public Snapshot getSnapshot() {
      NetworkTableEntry snapshot = m_table.getEntry("snapshot");
      double snshot = snapshot.getDouble(0.0);
      Snapshot mode = Snapshot.getByValue(snshot );        
      return mode;
  }

  public enum  Advanced_Target {

    kone(0), ktwo(1), kthree(2);
  
    private static final Map<Integer,  Advanced_Target> MY_MAP = new HashMap<Integer,  Advanced_Target>();
    
    static {
      for ( Advanced_Target  Advanced_Target : values()) {
        MY_MAP.put( Advanced_Target.getValue(),  Advanced_Target);
      }
    }
    
    private Integer value;
  
    private  Advanced_Target(Integer value) {
      this.value = value;
    }
  
    public Integer getValue() {
      return value;
    }
  
    public static  Advanced_Target getByValue(Integer value) {
      return MY_MAP.get(value);
    }
  
    public String toString() {
      return name();
    }
  
  }

 // *************** Advanced Usage with Raw Contours *********************   

    /**
     * Limelight posts three raw contours to NetworkTables that are not influenced by your grouping mode. 
     * That is, they are filtered with your pipeline parameters, but never grouped. X and Y are returned 
     * in normalized screen space (-1 to 1) rather than degrees.	 * 
     */

    public double getAdvanced_RotationToTarget(Advanced_Target raw) {
      NetworkTableEntry txRaw = m_table.getEntry("tx" + Integer.toString(raw.getValue()));
      double x = txRaw.getDouble(0.0);
      return x;
  }

  public double getAdvanced_degVerticalToTarget(Advanced_Target raw) {
      NetworkTableEntry tyRaw = m_table.getEntry("ty" + Integer.toString(raw.getValue()));
      double y = tyRaw.getDouble(0.0);
      return y;
  }

  public double getAdvanced_TargetArea(Advanced_Target raw) {
      NetworkTableEntry taRaw = m_table.getEntry("ta" + Integer.toString(raw.getValue()));
      double a = taRaw.getDouble(0.0);
      return a;
  }
  
  public double getAdvanced_Skew_Rotation(Advanced_Target raw) {
      NetworkTableEntry tsRaw = m_table.getEntry("ts" + Integer.toString(raw.getValue()));
      double s = tsRaw.getDouble(0.0);
      return s;
  }

  public enum  Advanced_Crosshair {

    kone(0), ktwo(1);
  
    private static final Map<Integer,  Advanced_Crosshair> MY_MAP = new HashMap<Integer,  Advanced_Crosshair>();
    
    static {
      for ( Advanced_Crosshair  Advanced_Crosshair : values()) {
        MY_MAP.put( Advanced_Crosshair.getValue(),  Advanced_Crosshair);
      }
    }
    
    private Integer value;
  
    private  Advanced_Crosshair(Integer value) {
      this.value = value;
    }
  
    public Integer getValue() {
      return value;
    }
  
    public static  Advanced_Crosshair getByValue(Integer value) {
      return MY_MAP.get(value);
    }
  
    public String toString() {
      return name();
    }
  
  }

  //Raw Crosshairs:
    //If you are using raw targeting data, you can still utilize your calibrated crosshairs:
    
    public double[] getAdvanced_RawCrosshair(Advanced_Crosshair raw){
      double[] crosshars = new double[2];
      crosshars[0] = getAdvanced_RawCrosshair_X(raw);
      crosshars[1] = getAdvanced_RawCrosshair_Y(raw);
      return crosshars;
  }
  public double getAdvanced_RawCrosshair_X(Advanced_Crosshair raw) {
      NetworkTableEntry cxRaw = m_table.getEntry("cx" + Integer.toString(raw.getValue()));
      double x = cxRaw.getDouble(0.0);
      return x;
  }

  public double getAdvanced_RawCrosshair_Y(Advanced_Crosshair raw) {
      NetworkTableEntry cyRaw = m_table.getEntry("cy" + Integer.toString(raw.getValue()));
      double y = cyRaw.getDouble(0.0);
      return y;
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
