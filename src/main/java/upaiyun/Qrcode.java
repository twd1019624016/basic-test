package upaiyun;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

public class Qrcode {

	public static void main(String[] args) {
		String filePath = "C:\\Users\\tian\\Desktop\\QQ图片20170610162720.png";
		String decodeQr = decodeQr(filePath);

		System.out.println(decodeQr);
	}

	public static String decodeQr(String filePath) {
		String retStr = "";
		if ("".equalsIgnoreCase(filePath) && filePath.length() == 0) {
			return "图片路径为空!";
		}
		try {
			BufferedImage bufferedImage = ImageIO.read(new FileInputStream(filePath));
			LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap bitmap = new BinaryBitmap(binarizer);
			HashMap<DecodeHintType, Object> hintTypeObjectHashMap = new HashMap<>();
			hintTypeObjectHashMap.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			Result decode = new MultiFormatReader().decode(bitmap, hintTypeObjectHashMap);
			retStr = decode.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retStr;
	}
}
