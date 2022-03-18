package controller;

import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @author by pepsi-wyl
 * @date 2022-02-27 19:59
 */
@Controller(value = "fileController")
@RequestMapping(name = "文件操作", value = "/file")
public class FileController {

    @SneakyThrows
    @RequestMapping(name = "下载文件", value = "/down", method = RequestMethod.GET)
    public String down(HttpServletRequest request, HttpServletResponse response) {

        String path = request.getServletContext().getRealPath("/static/file");
        String fileName = "1.jpg";

        //设置response 响应头
        response.reset();    //设置页面不缓存,清空buffer
        response.setCharacterEncoding("UTF-8");            //字符编码
        response.setContentType("multipart/form-data");    //二进制传输数据
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));

        InputStream input = new FileInputStream(new File(path, fileName));
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

    @SneakyThrows
    @RequestMapping(name = "下载文件", value = "/fileDown")
    public ResponseEntity<byte[]> fileDown(HttpSession session) {

        String path = session.getServletContext().getRealPath("/static/file");  // 获取项目的路径
        String fileName = "1.jpg";                                                 // 文件名称

        InputStream input = new FileInputStream(new File(path, fileName));   //创建输入流
        byte[] bytes = new byte[input.available()];                          //创建字节数组
        input.read(bytes);                                                   //将流读到字节数组中

        MultiValueMap<String, String> headers = new HttpHeaders(); //创建HttpHeaders对象设置响应头信息
        headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8")); //设置要下载方式以及下载文件的名字
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, HttpStatus.OK); //创建ResponseEntity对象
        input.close(); //关闭输入流
        return responseEntity;
    }

    @ResponseBody
    @SneakyThrows
    @RequestMapping(name = "上传文件", value = "/fileUp", method = RequestMethod.POST)
    public String fileDown(@RequestParam("file") MultipartFile file, HttpSession session) {
        File realPath = new File(session.getServletContext().getRealPath("/static/file")); // 获取服务器上传文件的路径
        if (!realPath.exists()) realPath.mkdir();     // 文件夹不存在则创建
        String filename = file.getOriginalFilename(); // 获取文件名称
        file.transferTo(new File(realPath + File.separator + UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf(".")))); // 上传文件
        return "success";
    }
}
