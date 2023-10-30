package antvictor.study.watermarking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.opencv.opencv_java;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import static org.opencv.core.Core.NATIVE_LIBRARY_NAME;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;

/**
 * @author Antvictor
 * @date 2023/10/27
 **/
public class TestOpenCV {


    /**
     * @author yangxiaohui
     * @Date: Create by 2018-10-25 19:42
     * @Description:
     */
    static {
        //加载opencv动态库
        Loader.load(opencv_java.class);
    }

    public static void main(String[] args) {
        Mat img = imread("/Users/exccedy/Downloads/nfrpc.jpg");//加载图片
        Mat outImg = ImgWatermarkUtil.addImageWatermarkWithText(img, "哈哈哈哈");
        imwrite("/Users/exccedy/Downloads/nfrpc_out.jpg", outImg);//保存加过水印的图片
        //读取图片水印
        Mat watermarkImg = ImgWatermarkUtil.getImageWatermarkWithText(outImg);

        imwrite("/Users/exccedy/Downloads/nfrpc_out_watermark.jpg", watermarkImg);//保存获取到的水印
    }

}
