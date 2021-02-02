package com.papaxiong.support;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liukaiyang
 * @date 2018/3/16 18:08
 */
public class ImageUtil {

    /**
     * 将宽度相同的图片，竖向追加在一起 ##注意：宽度必须相同
     *
     * @param imageList 文件流数组
     */
    public static byte[] combineImageVertical(List<BufferedImage> imageList) throws IOException {
        int height = 0, // 总高度
                width = 0, // 总宽度
                _height = 0, // 临时的高度 , 或保存偏移高度
                __height = 0, // 临时的高度，主要保存每个高度
                picNum = imageList.size();// 图片的数量
        int[] heightArray = new int[picNum]; // 保存每个文件的高度
        BufferedImage buffer = null; // 保存图片流
        List<int[]> imgRGB = new ArrayList<>(); // 保存所有的图片的RGB
        int[] _imgRGB; // 保存一张图片中的RGB数据
        for (int i = 0; i < picNum; i++) {
            buffer = imageList.get(i);
            heightArray[i] = _height = buffer.getHeight();// 图片高度
            if (i == 0) {
                width = buffer.getWidth();// 图片宽度
            }
            height += _height; // 获取总高度
            _imgRGB = new int[width * _height];// 从图片中读取RGB
            _imgRGB = buffer
                    .getRGB(0, 0, width, _height, _imgRGB, 0, width);
            imgRGB.add(_imgRGB);
        }
        _height = 0; // 设置偏移高度为0
        // 生成新图片
        BufferedImage imageResult = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_BGR);
        for (int i = 0; i < picNum; i++) {
            __height = heightArray[i];
            if (i != 0)
                _height += __height; // 计算偏移高度
            imageResult.setRGB(0, _height, width, __height, imgRGB.get(i),
                    0, width); // 写入流中
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(imageResult, "JPEG", out);// 写图片
        byte[] b = out.toByteArray();
        out.close();
        return b;
    }
}
