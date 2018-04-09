package upaiyun;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import main.java.com.UpYun;
import main.java.com.UpYun.FolderItem;
import main.java.com.UpYun.PARAMS;
import main.java.com.upyun.FormUploader;
import main.java.com.upyun.Params;
import main.java.com.upyun.Result;
import main.java.com.upyun.ResumeUploader;
import main.java.com.upyun.SignatureListener;
import main.java.com.upyun.UpYunUtils;
import net.coobird.thumbnailator.Thumbnails;

public class Demo {
	private static final String BUCKET_NAME = "huifeideyu2";
	private static final String OPERATOR_NAME = "wangmazi";
	private static final String OPERATOR_PWD = "wangmazi@tdp";
	static final UpYun upYun = new UpYun("huifeideyu2", "wangmazi", "wangmazi@tdp");

	// 可选属性
	static {
		upYun.setDebug(true);
		upYun.setTimeout(60);
		upYun.setApiDomain(UpYun.ED_AUTO);// 设置网路接入点，联通，移动，电信

	}

	/**
	 * 1.若空间内指定目录已存在相同文件，则会被覆盖，且不可逆。若要避免此情况， 可以先通过获取文件信息来判断是否已经存在相同文件
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		/*
		 * userName = wangmazi password = wangmazi@tdp backetName = huifeideyu2
		 * 
		 */
		// 初始化UpYun
		// UpYun upYun = new UpYun("huifeideyu", "cy123456","cy123456");
		UpYun upYun1 = new UpYun("huifeideyu2", "wangmazi", "wangmazi");

		// 可选属性
		upYun1.setDebug(true);
		upYun1.setTimeout(60);
		upYun1.setApiDomain(UpYun.ED_AUTO);// 设置网路接入点，联通，移动，电信

		// 创建目录
		boolean mkDir = upYun1.mkDir("/dir1/dir2/", true);

		/*
		 * String path =
		 * "E:\\htdocs\\www\\npstore\\upload\\20161224\\1482563985089!160.jpg";
		 */
		String path = "D:/app-taodangpu-release-1.0.apk";
		// 待上传的文件
		File file = new File(path);
		System.out.println(file.getPath());
		boolean exists = file.exists();
		// 设置Md5 加密

		upYun1.setContentMD5(UpYun.md5(file));

		/* upYun.setFileSecret(""); */

		// 上传文件
		System.out.println(upYun1.getPicHeight() + ":" + upYun1.getPicType());
		System.out.println("---------上传后-------------------");
		boolean writeFile = upYun1.writeFile("/dir1/dir2/upload9-322.apk", file, true);
		System.out.println(upYun1.getPicHeight() + ":" + upYun1.getPicType());
		System.out.println(writeFile);

	}

	@Test
	public void sonFiles() {
		String path = "C:/Users/tian/Pictures/Saved Pictures";
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory()) {
			File[] listFiles = dir.listFiles();
			for (File file : listFiles) {
				System.out.println(file.getName());
			}
		}
	}

	@Test
	public void upload() throws IOException {
		upYun.setFileSecret("");
		String path = "C:/Users/tian/Pictures/Saved Pictures";
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory()) {
			File[] listFiles = dir.listFiles();
			for (File file : listFiles) {
				boolean writeFile = upYun.writeFile("/" + file.getName() + 33333, file, true);
				System.out.println(file.getName() + 3335433);
				return;
			}
		}
	}

	/**
	 * 获取文件目录列表,只是读取当前目录下的文件，包括目录，不会递归 显示信息 包括名字--大小--类型--日期 文件夹的大小为0
	 */
	@Test
	public void getDir() {
		String path = "/dir1/";

		List<FolderItem> folderItems = upYun.readDir(path);

		for (FolderItem folderItem : folderItems) {
			System.out.println(folderItem.name + ":" + folderItem.type + ":" + folderItem.size + ":" + folderItem.date);
		}
	}

	/**
	 * 上传文件前需要判断文件是否存在，不然会发生覆盖 不存在返回null Map 包含3个Key： type 文件类型 size 文件大小 date
	 * 创建日期
	 * 
	 */
	@Test
	public void getFileInfo() {
		String path = "/dir1/222";

		Map<String, String> fileInfo = upYun.getFileInfo(path);
		System.out.println(fileInfo);

	}

	/**
	 * 获取空间使用量
	 */
	@Test
	public void getUsage() {
		System.out.println(upYun.getBucketUsage());
		System.out.println(upYun.getFolderUsage("/dir1/"));
	}

	/**
	 * 文件下载
	 */
	@Test
	public void readFile() {
		String filePath = "/dir1/dir2/upload9-322.jpg";
		String datas = upYun.readFile(filePath); // 获取文件内容为String

		boolean result = upYun.readFile(filePath, new File("C:/Users/tian/Desktop/9-3.jpg"));// 文件输出到指定的路径
	}

	/**
	 * 删除文件，若文件不存在会报异常
	 */
	@Test
	public void deleteFile() {
		String filePath = "/dir1/dir2/upload9-322.jpg";
		boolean deleteFile = upYun.deleteFile(filePath);
		System.out.println(deleteFile);
	}

	/**
	 * 断点上传---待测
	 */
	public void eee() {

		ResumeUploader resumeUploader = new ResumeUploader(BUCKET_NAME, OPERATOR_NAME, OPERATOR_PWD);

		resumeUploader.setOnProgressListener(new ResumeUploader.OnProgressListener() {

			public void onProgress(int index, int total) {

			}
		});

	}

	/**
	 * @throws IOException
	 *             制作缩略图
	 */
	@Test
	public void Pichandle() throws IOException {
		Map<String, String> params = new HashMap<>();
		/*
		 * params.put(PARAMS.KEY_X_GMKERL_TYPE.getValue(),PARAMS.VALUE_FIX_BOTH.
		 * getValue() );//设置缩略图的类型
		 * params.put(PARAMS.KEY_X_GMKERL_VALUE.getValue(),"200x130");//设置缩略图的参数
		 * params.put(PARAMS.KEY_X_GMKERL_QUALITY.getValue(),"30");//设置缩略图的质量，
		 * 默认95
		 */
		params.put(PARAMS.KEY_X_GMKERL_ROTATE.getValue(), PARAMS.VALUE_ROTATE_90.getValue());
		File file = new File("C:/Users/tian/Desktop/9-31111.ccc");
		String filePath = "/dir1/dir2/4444445.jpg";

		/* boolean writeFile = upYun.writeFile(filePath, file, true, params); */
		boolean writeFile = upYun.writeFile(filePath, file, true);
		boolean result = upYun.readFile(filePath, new File("C:/Users/tian/Desktop/9-31111.jpg"));
	}

	/**
	 * 表单上传接口 ----------------------- 测试
	 */
	private static final String APIKEY = "RP9vVXBUYWw4xGtV2udxfvZCGxE=";

	@Test
	public void formUploader() {
		SignatureListener signatureListener = new SignatureListener() {

			@Override
			public String getSignature(String raw) {

				return UpYunUtils.md5(raw + APIKEY);
			}
		};
		String savePath = "/dir1/ttttt.jpg";
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put(Params.SAVE_KEY, savePath);

		String path = "C:/Users/tian/Pictures/Saved Pictures/14307187_1349676294938_100x75.jpg";
		File file = new File(path);
		System.out.println(file.exists());

		/*
		 * FormUploader uploader = new FormUploader(BUCKET_NAME, APIKEY, null);
		 * Result result = uploader.upload(paramsMap, file);
		 * System.out.println(result);
		 */

		// 初始化表单接口
		FormUploader uploader = new FormUploader(BUCKET_NAME, null, signatureListener);

		// 设置初始化参数
		uploader.setTimeout(30);
		uploader.setApiDomain(UpYun.ED_AUTO);
		uploader.setExpiration(1800);

		Result result2 = uploader.upload(paramsMap, file);
		System.out.println(result2);

		System.out.println(uploader.upload(paramsMap, "test1".getBytes()));

		/*
		 * FormUploader uploader2 = new FormUploader(BUCKET_NAME, null,
		 * signatureListener); Result result2 = uploader2.upload(paramsMap,
		 * file); System.out.println(result2);
		 * 
		 * System.out.println(uploader.upload(paramsMap, "test1".getBytes()));
		 */

	}

	@Test
	public void testSync() {
		// 初始化uploader
		FormUploader uploader = new FormUploader(BUCKET_NAME, APIKEY, null);

		// 初始化参数组 Map
		final Map<String, Object> paramsMap = new HashMap<String, Object>();

		// 添加 SAVE_KEY 参数
		String savePath = "/dir1/ttttt2.jpg";
		paramsMap.put(Params.SAVE_KEY, savePath);

		// 添加同步上传作图参数 X_GMKERL_THUMB
		paramsMap.put(Params.X_GMKERL_THUMB, "/fw/300/unsharp/true/quality/80/format/png");

		// 打印结果
		String path = "C:/Users/tian/Pictures/Saved Pictures/14307187_1349676294938_100x75.jpg";
		File file = new File(path);
		System.out.println(file.exists());
		System.out.println(uploader.upload(paramsMap, file));
	}

	@Test
	public void testAsync() {
		// uploader
		FormUploader uploader = new FormUploader(BUCKET_NAME, APIKEY, null);

		// 初始化参数组 Map
		final Map<String, Object> paramsMap = new HashMap<String, Object>();

		String savePath = "/dir1/ttttt.jpg";
		// 添加 SAVE_KEY 参数
		paramsMap.put(Params.SAVE_KEY, savePath);

		// 初始化JSONArray
		JSONArray array = new JSONArray();

		// 初始化JSONObject
		JSONObject json = new JSONObject();

		// json 添加 name 属性
		json.put("name", "thumb");

		// json 添加 X_GMKERL_THUMB 属性
		json.put(Params.X_GMKERL_THUMB, "/fw/300/unsharp/true/quality/80/format/png");

		// json 添加 save_as 属性
		json.put("save_as", "/path/to/fw_100.jpg");

		// json 添加 notify_url 属性
		json.put("notify_url", "http://httpbin.org/post");

		// 将json 对象放入 JSONArray
		array.put(json);

		// 添加异步作图参数 APPS
		paramsMap.put(Params.APPS, array);

		// 打印结果
		String path = "C:/Users/tian/Pictures/Saved Pictures/14307187_1349676294938_100x75.jpg";
		File file = new File(path);
		System.out.println(uploader.upload(paramsMap, file));
	}

	@Test
	public void testFile() {
		System.out.println(System.getProperty("os.name"));
		String path = "C:/Users/tian/Pictures/Saved Pictures/14307187_1349676294938_100x75.jpg";
		File file = new File(path);
		System.out.println(file.exists());
	}

	/**
	 * @throws IOException
	 * 
	 */
	int width = 1000;
	int height = 1000;

	@Test
	public void testThumb() throws IOException {

		File file = new File("C:\\Users\\tian\\Desktop\\ddd444eee.jpg");
		BufferedImage image = ImageIO.read(file);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		/* ImageIO.write(image, "", baos); */

		/*
		 * Thumbnails.of(image).size(1000, 1000)
		 * .keepAspectRatio(true).outputQuality(0.8)
		 * .outputFormat("png").toOutputStream(baos);
		 */
		ImageIO.write(image, "ico", baos);

		byte[] byteArray = baos.toByteArray();
		for (byte b : byteArray) {

		}
		if (image.getHeight() > width || image.getWidth() > height) {
			Thumbnails.of(new File("C:\\Users\\tian\\Desktop\\ddd444eee.jpg"))
					.size(Integer.valueOf(500), Integer.valueOf(500)).keepAspectRatio(true).outputQuality(0.8)
					.useOriginalFormat().toFile(new File("C:\\Users\\tian\\Desktop\\ddd444.jpg"));
		}

	}

}
