/**
 * 
 */
package blog163.visitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenguangjian 2015年3月15日 下午12:54:49
 */
public class Utils {
	public static void main(String[] args) throws IOException {
		String path = Messages.getString("WebTools.UrlFilePath");
		System.out.println(getUrls(path));
	}

	public static List<String> getUrls(String filePath) throws IOException {
		List<String> urls = new ArrayList<String>();

		File f = new File(filePath);
		InputStreamReader r = new InputStreamReader(new FileInputStream(f));
		BufferedReader bufferedReader = new BufferedReader(r);
		String lineTxt = "";
		StringBuffer sb = new StringBuffer();
		while ((lineTxt = bufferedReader.readLine()) != null) {
			// System.out.println(lineTxt);
			sb.append(lineTxt);
		}
		r.close();

		// System.out.println(sb);
		String srcTxt = sb.toString();

		String regex = "<a href=\"http://(.+?).blog.163.com/(.+?)\">(.+?)</a>";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(srcTxt);
		while (m.find()) {
			String url = m.group(0);
			int s = url.indexOf("http://");
			int e = url.indexOf("\">");
			if (s > e) {
				continue;
			}
			url = url.substring(s, e);
			System.out.println(url);
			urls.add(url);
		}

		return urls;

	}

}
