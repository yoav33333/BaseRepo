package org.firstinspires.ftc.teamcode.PurePursuit.pathPlanning;

import com.arcrobotics.ftclib.geometry.Pose2d;

import java.util.HashMap;
import java.util.List;

public class PathPlanning {
    public HashMap<Integer, Pose2d> Points = new HashMap<Integer, Pose2d>();
    public List<Pose2d> MainPoints;
    public PathPlanning(List<Pose2d> MainPoints) {
        this.MainPoints = MainPoints;

    }
    //TODO actual path planning
    public void MakePath(){
        for (int i = 0; i < MainPoints.size(); i++) {
            Points.put(i, MainPoints.get(i));
        }
    }

    //make an bezier curve


}
