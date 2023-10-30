package antvictor.study.watermarking;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.BitSet;

/**
 * @author Antvictor
 * @date 2023/10/26
 **/
public class BlindWaterMarkingLSB {
    public static void main(String[] args) throws IOException {
        set("/Users/exccedy/Downloads/nfrpc.jpg", "哈哈哈");
        get("/Users/exccedy/Downloads/watermarked.jpg");

    }

    /**
     * 设置水印
     *
     * @param image
     * @param watermark
     * @throws IOException
     */
    public static void set(String image, String watermark) throws IOException {
        // 水印信息

// 转为字节数组
        byte[] watermarkBytes = watermark.getBytes();

// 加密(此处略去)

// 转为比特流
        BitSet watermarkBits = BitSet.valueOf(watermarkBytes);
        // 读取主图像
        BufferedImage mainImage = ImageIO.read(new File(image));

// 获取像素数据
        int[][] pixels = getPixels(mainImage);
        // 嵌入水印比特流
        embedWatermark(pixels, watermarkBits);

// 更新主图像像素
        setPixels(mainImage, pixels);

        ImageIO.write(mainImage, "png", new File("/Users/exccedy/Downloads/watermarked.jpg"));
    }

    /**
     * 获取水印
     *
     * @param watermarkedImagePath
     * @throws IOException
     */
    public static void get(String watermarkedImagePath) throws IOException {
        BufferedImage watermarkedImage = ImageIO.read(new File(watermarkedImagePath));
        // 从水印图像提取比特流
        BitSet extractedBits = extractWatermark(watermarkedImage);

// 解密(略)

// 转为字节数组
        byte[] extractedBytes = extractedBits.toByteArray();

// 转为字符串
        String extractedStr = new String(extractedBytes);
        System.out.println(extractedStr);
    }

    public static int[][] getPixels(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        int[][] pixels = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[i][j] = image.getRGB(i, j);
            }
        }

        return pixels;
    }

    // 嵌入水印比特流
    public static void embedWatermark(int[][] pixels, BitSet watermark) {
        int index = 0;
        // 假设使用LSB算法嵌入
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                int rgb = pixels[i][j];

                int red = getRed(rgb);
                int green = getGreen(rgb);
                int blue = getGreen(rgb);
                red = setLSB(red, watermark.get(index) ? 1 : 0);
                green = setLSB(green, watermark.get(index + 1) ? 1 : 0);
                blue = setLSB(blue, watermark.get(index + 2) ? 1 : 0);

                // 同样嵌入到green和blue
                int newRGB = getRGB(red, green, blue);
                pixels[i][j] = newRGB;

                index+=3;
            }
        }
    }

    // 更新主图像像素
    public static void setPixels(BufferedImage image, int[][] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                image.setRGB(i, j, pixels[i][j]);
            }
        }
    }

    // 从水印图像提取比特流
    public static BitSet extractWatermark(BufferedImage image) {
        int index = 0;
        int width = image.getWidth();
        int height = image.getHeight();

        int[][] pixels = getPixels(image);

        BitSet extracted = new BitSet();

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {

                int rgb = pixels[i][j];

                int red = getRed(rgb);
                int blue = getBlue(rgb);
                int green = getGreen(rgb);
                int bit = getLSB(red);
                extracted.set(index, bit == 1);
                bit = getLSB(green);
                extracted.set(index+1, bit == 1);
                bit = getLSB(blue);
                extracted.set(index+2, bit == 1);
                // 同样从green和blue中提取


                index+=3;
            }
        }

        return extracted;
    }

    public static int getRed(int rgb) {
        return (rgb >> 16) & 0xFF;
    }

    public static int getBlue(int rgb) {
        return rgb & 0xFF;
    }

    public static int getGreen(int rgb) {
        return (rgb >> 8) & 0xFF;
    }

    public static int setLSB(int value, int bit) {
        return (value & 0xFE) | bit;
    }

    public static int getRGB(int red, int green, int blue) {
        return (red << 16) | (green << 8) | blue;
    }

    public static int getLSB(int value) {
        return value & 1;
    }
}
