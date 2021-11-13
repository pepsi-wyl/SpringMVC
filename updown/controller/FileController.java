package controller;

/**
 * @author by wyl
 * @date 2021/11/13.09点35分
 */

import lombok.SneakyThrows;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * 文件上传和下载
 */

@Controller(value = "fileController")
@Scope("singleton")

@RequestMapping("/file")
public class FileController {

    @ResponseBody
    @SneakyThrows
    @RequestMapping(value = "/up", method = RequestMethod.POST)
    public String up(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
        //上传路径保存设置
        File realPath = new File(request.getServletContext().getRealPath("/upload"));
        if (!realPath.exists()) realPath.mkdir();
        //开始上传
        file.transferTo(new File(realPath + "/" + file.getOriginalFilename()));
        return "success";
    }


    @SneakyThrows
    @RequestMapping(value = "/down", method = RequestMethod.GET)
    public String down(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getServletContext().getRealPath("/upload");
        String fileName="[简单词]大学四级高频800词.pdf";
        //设置response 响应头
        response.reset();    //设置页面不缓存,清空buffer
        response.setCharacterEncoding("UTF-8");           //字符编码
        response.setContentType("multipart/form-data");    //二进制传输数据
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));

        File file = new File(path, fileName);

        //2、 读取文件--输入流
        InputStream input = new FileInputStream(file);
        //3、 写出文件--输出流
        OutputStream out = response.getOutputStream();

        byte[] buff = new byte[1024];
        int index = 0;
        //4、执行 写出操作
        while ((index = input.read(buff)) != -1) {
            out.write(buff, 0, index);
            out.flush();
        }

        out.close();
        input.close();
        return null;
    }


}
