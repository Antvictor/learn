package antvictor.study.watermarking;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.opencv.core.Core.addWeighted;
import static org.opencv.videoio.Videoio.*;

/**
 * @author Antvictor
 * @date 2023/10/31
 **/
@Slf4j
public class VideoWatermarkUtil {

    public static void main(String[] args) {
       /* // 加载视频文件
        VideoCapture capture = new VideoCapture("/Users/exccedy/Downloads/nfr-test.mp4");

        // 加载水印图片
        Mat logo = Imgcodecs.imread("/Users/exccedy/Downloads/logo.png");

        // 创建 VideoWriter 对象
        Size frameSize = new Size(capture.get(CAP_PROP_FRAME_WIDTH), capture.get(CAP_PROP_FRAME_HEIGHT));
        int fourcc = VideoWriter.fourcc('M', 'J', 'P', 'G');
        double fps = capture.get(CAP_PROP_FPS);
        VideoWriter writer = new VideoWriter("/Users/exccedy/Downloads/output.mp4", fourcc, fps, frameSize);

        while (true) {
            Mat frame = new Mat();

            //读取视频帧
            capture.read(frame);

            // 将帧转换为灰度图
            Mat gray = new Mat();
            Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGR2GRAY);

// 对灰度帧进行DFT变换
            Mat dft = new Mat();
            Core.dft(gray, dft);


            if (!frame.empty()) {
                Mat overlay = frame.clone();
                Mat logoResize = new Mat();
                Size size = new Size(overlay.cols(), overlay.rows());
                Imgproc.resize(logo, logoResize, size);
                addWeighted(overlay, 1, logoResize, 0.3, 0, overlay);

                Core.addWeighted(dft_frame_real, 1.0, dft_watermark_real, 0.1, 0, dft_frame_real);

                //将添加水印的帧写入输出视频
                writer.write(overlay);
            } else {
                break;
            }
        }
        // 释放资源
        writer.release();
        capture.release();*/

        // 加载视频和水印图片
        VideoCapture capture = new VideoCapture("/Users/exccedy/Downloads/nfr-test.mp4");
        Mat logo =  Imgcodecs.imread("/Users/exccedy/Downloads/logo.png");
        Mat logoGray = new Mat();
        Imgproc.cvtColor(logo, logoGray, Imgproc.COLOR_BGR2GRAY);

        logoGray.convertTo(logoGray, CvType.CV_32F);
// 初始化视频写入器
        Size frameSize = new Size(capture.get(CAP_PROP_FRAME_WIDTH), capture.get(CAP_PROP_FRAME_HEIGHT));
        int fourcc = VideoWriter.fourcc('M', 'J', 'P', 'G');
        double fps = capture.get(CAP_PROP_FPS);
        VideoWriter writer = new VideoWriter("/Users/exccedy/Downloads/output.mp4", fourcc, fps, frameSize);

        while(true) {

            Mat frame = new Mat();
            capture.read(frame);

            if (frame.empty()) {
                break;
            }

            // 将帧转换为灰度图
            Mat gray = new Mat();
            Mat preserving = new Mat();
            Mat realGay = new Mat();
            Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGR2GRAY);
            Imgproc.cvtColor(frame, realGay, Imgproc.COLOR_BGR2GRAY);
            realGay.convertTo(preserving, CvType.CV_32F);
            // 对灰度帧进行DFT变换
            Mat dft = new Mat();
            Core.dft(preserving, dft);

            // 将水印图进行DFT变换
            Mat dft_logo = new Mat();
            Core.dft(logoGray, dft_logo);

            // 获取DFT的实部和虚部
//            Mat[] planes = new Mat[2];
            Mat real = new Mat();
            Mat imag = new Mat();
            List<Mat> planes = new ArrayList<>();
            planes.add(real);
            planes.add(imag);
            Core.split(dft, planes);

//            Mat[] planes_logo = new Mat[2];
            Mat real_logo = new Mat();
            Mat imag_logo = new Mat();
            List<Mat> planes_logo = new ArrayList<>();
            planes_logo.add(real_logo);
            planes_logo.add(real_logo);
            Core.split(dft_logo, planes_logo);

            // 在实部中添加水印
            Core.addWeighted(real, 1.0, real_logo, 0.1, 0, real);

            // 合并实部和虚部
            Core.merge(planes, dft);

            // 对修改后的DFT进行反变换,得到嵌入水印的帧
            Core.idft(dft, frame);

            // 将帧写入输出视频
            writer.write(frame);

        }

        writer.release();
        capture.release();
    }
}
