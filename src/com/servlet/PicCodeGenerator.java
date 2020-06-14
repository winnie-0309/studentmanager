package com.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PicCodeGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public PicCodeGenerator() {
		super();
	}
	public void destroy() {
		super.destroy();
	}

	
	private String getCode(int size) {
		StringBuilder sb = new StringBuilder();
		String chars = "0123456789abcdefghijklmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
		for (int i = 0; i < size; i++) {
			sb.append(chars.charAt(new Random().nextInt(chars.length())));
		}
		return sb.toString();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置不缓存图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// 输出验证码图片
		/*
		 * 1.绘图
		 */
		// 先创建一个画布(内存映像对象)
		BufferedImage image = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
		// 获得画笔
		Graphics g = image.getGraphics();
		// 设置画笔颜色，用来画背景
		g.setColor(new Color(255, 255, 255));
		// 给画布设置背景
		g.fillRect(0, 0, 100, 30);
		// 再次设置画笔颜色，用来画前景
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		// 设置字体(字体,风格,大小)
		g.setFont(new Font("Microsoft Yahei", Font.BOLD | Font.ITALIC, 22));
		// 生成验证码
		String number = getCode(5);
		// 在画布上绘图
		g.drawString(number, 1, 25);// 此处的1和25是左下角的坐标
		// 加一些干扰线
		for (int i = 0; i < 8; i++) {
			g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r.nextInt(30));
		}
		/*
		 * 2.将图片压缩，然后输出，发送给客户端
		 */
		// 设置消息头:content-type:image/jpeg
		response.setContentType("image/jpeg");
		// 获得字节输出流(图片是二进制数据)
		OutputStream os = response.getOutputStream();
		// 将原始图片(image)按照指定的算法压缩(jpeg),将压缩之后得到的字节写入response对象
		ImageIO.write(image, "JPEG", os);
		os.close();
		
		//3.将验证码(number)绑定到session对象上
		request.getSession().setAttribute("checkcode", number);
		
	}
}
