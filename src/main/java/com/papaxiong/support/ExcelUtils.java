package com.papaxiong.support;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.papaxiong.support.model.KeyValuePair;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author zhaoqi
 * @since 2020-12-20
 */
public class ExcelUtils {


    public static  <T> void export(String title, List<T> rows, List<KeyValuePair> keys, HttpServletResponse response) {
        ExcelWriter writer = ExcelUtil.getWriter(true);

        ServletOutputStream out = null;

        try {
            //headers
            keys.forEach(p -> writer.addHeaderAlias(p.getKey(), p.getValue()));
            writer.merge(keys.size() - 1, title);
            //rows
            writer.write(rows, true);


            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setHeader("Content-Disposition","attachment;filename=export.xlsx");

            out = response.getOutputStream();

            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭writer，释放内存
            writer.close();
            //此处记得关闭输出Servlet流
            if (out != null) {
                IoUtil.close(out);
            }
        }

    }
}
