package com.wf.common.utils;

import com.google.common.collect.ImmutableMap;
import com.wf.common.exception.WFException;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.Map;

public class FileUtils {

	/**
	 * 获取文件扩展名
	 *
	 * @param filename
	 * @return
	 */
	public static String getExtend(String filename) {
		return getExtend(filename, "");
	}
	/**
	 * 获取文件扩展名
	 *
	 * @param filename
	 * @return
	 */
	public static String getExtend(String filename, String defExt) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');

			if ((i > 0) && (i < (filename.length() - 1))) {
				return (filename.substring(i+1)).toLowerCase();
			}
		}
		return defExt.toLowerCase();
	}

	/**
	 * 文件复制
	 *方法摘要：这里一句话描述方法的用途
	 *@param
	 *@return void
	 */
	public static void copyFile(String inputFile,String outputFile) throws FileNotFoundException {
		File sFile = new File(inputFile);
		File tFile = new File(outputFile);
		FileInputStream fis = new FileInputStream(sFile);
		FileOutputStream fos = new FileOutputStream(tFile);
		int temp = 0;
		byte[] buf = new byte[10240];
		try {
			while((temp = fis.read(buf))!=-1){
				fos.write(buf, 0, temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String readFile(InputStream input) throws IOException {
		if (input != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf-8"));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} else {
			return null;
		}
	}

	public static final Map<String, String> pictureMap = ImmutableMap.<String, String>builder()
			.put("bmp", "0")
			.put("dib", "1")
			.put("gif", "2")
			.put("jfif", "3")
			.put("jpe", "4")
			.put("jpeg", "5")
			.put("jpg", "6")
			.put("png", "7")
			.put("tif", "8")
			.put("tiff", "9")
			.put("ico", "10")
			.build();
	/**
	 * 判断文件是否为图片
	 * @param filename
	 *            文件名
	 *            判断具体文件类型
	 * @return 检查后的结果
	 * @throws Exception
	 */
	public static boolean isPicture(String filename) {
		// 文件名称为空的场合
		if (StringUtils.isEmpty(filename)) {
			return false;
		}
		// 获得文件后缀名
		String tmpName = getExtend(filename);
		return pictureMap.get(tmpName)!=null;
	}
	public static void writeFile(File file, byte[] bytes) throws IOException {
		writeFile(file, bytes, false);
	}

	public static void writeFile(File file, byte[] bytes, boolean append) throws IOException {
		FileOutputStream bfout = null;
		try {
			bfout = new FileOutputStream(file, append);
			bfout.write(bytes);
			bfout.close();
		} finally {
			if (bfout != null) {
				bfout.close();
			}
		}
	}

	public static void writeFile(File target, InputStream input) throws IOException {
		if (input != null) {
			BufferedOutputStream out = null;
			try {
				BufferedInputStream bInput = new BufferedInputStream(input);
				out = new BufferedOutputStream(new FileOutputStream(target));
				byte[] bs = new byte[2*1024*1024];
				int readCount = -1;
				while ((readCount = bInput.read(bs)) > -1) {
					if (readCount > 0) {
						out.write(bs, 0, readCount);
					}
				}
			} finally {
				if (out != null) {
					out.close();
				}
				input.close();
			}
		}
	}

	/**
	 *
	 * 删除单个文件
	 *
	 * @param fileName 被删除的文件名
	 * @return 如果删除成功，则返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	/**
	 * 创建单个文件
	 * @param descFileName 文件名，包含路径
	 * @return 如果创建成功，则返回true，否则返回false
	 */
	public static boolean createFile(String descFileName) {
		File file = new File(descFileName);
		if (file.exists()) {
			return false;
		}
		if (descFileName.endsWith(File.separator)) {
			return false;
		}
		if (!file.getParentFile().exists()) {
			// 如果文件所在的目录不存在，则创建目录
			if (!file.getParentFile().mkdirs()) {
				return false;
			}
		}
		// 创建文件
		try {
			if (file.createNewFile()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ByteArrayOutputStream cloneInputStream(InputStream input) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = input.read(buffer)) > -1) {
				baos.write(buffer, 0, len);
			}
			baos.flush();
			return baos;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将网络图片URL转换为byte数组
	 * @param strUrl
	 * @return
	 * @throws Exception
	 */
	public static byte[] convertUrlToBytes(String strUrl) throws IOException{
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
			URL u = new URL(strUrl);
			BufferedImage image = ImageIO.read(u);

			//convert BufferedImage to byte array
			String ext = strUrl.substring(strUrl.lastIndexOf('.') + 1);
			ImageIO.write( image, ext, baos);
			baos.flush();

			return baos.toByteArray();
		}catch (Exception e){
			throw new IOException("转化失败", e);
		}
	}

	/**
	 * 将图片base64转化为文件
	 * @param base64
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static void convertBase64ToFile(String base64, File file) {
		if (base64 == null || file == null) {
			throw new WFException("生成文件失败，参数错误");
		}
		try {
			File dir = file.getParentFile();
			if(!dir.exists()) {
				dir.mkdirs();
			}

			if(!file.exists()) {
				file.createNewFile();
			}

				Files.write(Paths.get(file.getAbsolutePath()), Base64.getDecoder().decode(base64.replace("data:image/png;base64,", "")), StandardOpenOption.CREATE);
		} catch (IOException e) {
			throw new WFException("生成文件失败", e);
		}
	}

		/**
         * 根据字符串创建本地目录 并按照日期建立子目录返回
         *
         * @param path
         * @param subPath 子目录
         * @return
         * @throws Exception
         */
	public static String getFolder(String path, String subPath) throws Exception {
		path += File.separatorChar + subPath;
		File dir = new File(path);
		if (!dir.exists()) {
			try {
				dir.mkdirs();
			} catch (Exception e) {
				throw new Exception("目录创建失败", e);
			}
		}
		return path;
	}

	public static FileInputStream openInputStream(File file) throws IOException {
		if (file.exists()) {
			if (file.isDirectory()) {
				throw new IOException("File '" + file + "' exists but is a directory");
			} else if (!file.canRead()) {
				throw new IOException("File '" + file + "' cannot be read");
			} else {
				return new FileInputStream(file);
			}
		} else {
			throw new FileNotFoundException("File '" + file + "' does not exist");
		}
	}

}
