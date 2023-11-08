package org.firstinspires.ftc.teamcode.common.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class yellowPixelPipeline extends OpenCvPipeline {
    @Override
    public Mat processFrame(Mat input) {

        if(input == null)
            throw new IllegalArgumentException("Input Mat is Null!");

        Imgproc.GaussianBlur(input, input, new Size(4,4), 0);

        Mat yCrCb = new Mat();
        Imgproc.cvtColor(input, yCrCb, Imgproc.COLOR_RGB2YCrCb);

        Scalar lowThresh = new Scalar(0, 0, 0);
        Scalar highThresh = new Scalar(255, 255, 255);

        Mat thresh = new Mat();

        Core.inRange(yCrCb, lowThresh, highThresh, thresh);

        Core.bitwise_and(thresh,input,thresh);

        return thresh;
    }
}
