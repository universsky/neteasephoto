/**
 * 
 */
package seo.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;

import org.apache.http.client.ClientProtocolException;

/**
 * @author jack
 *
 */
public class BlogsDownloader {

	/**
	 * 
	 * @param html
	 * @param param
	 * @return ArrayList<String[]> getArticleUrlTitle
	 */
	public static ArrayList<String[]> getArticleUrlTitleByHtml(String html,
			Param param) {

		String articleUrlRegex = param.getArticleUrlRegex();
		System.out.println(articleUrlRegex);
		String articleUrlStartIndex = param.getArticleUrlStartIndex();
		String articleUrlEndIndex = param.getArticleUrlEndIndex();
		int articleUrlStartOffset = param.getArticleUrlStartOffset();
		int articleUrlEndOffset = param.getArticleUrlEndOffset();

		String articleTitleRegex = param.getArticleTitleRegex();
		String articleTitleStartIndex = param.getArticleTitleStartIndex();
		String articleTitleEndIndex = param.getArticleTitleEndIndex();
		int articleTitleStartOffset = param.getArticleTitleStartOffset();
		int articleTitleEndOffset = param.getArticleTitleEndOffset();

		ArrayList<String[]> articleUrlsTitles = new ArrayList<String[]>();
		// System.out.println(html);
		Matcher matcher = Tools.regexgMatch(articleUrlRegex, html);
		if (null == matcher) {
			return null;
		}
		while (matcher.find()) {
			String articleUrlTitle = matcher.group();

			System.out.println(articleUrlRegex);
			System.out.println(articleUrlTitle);

			int s = articleUrlTitle.indexOf(articleUrlStartIndex)
					+ articleUrlStartOffset;
			int e = articleUrlTitle.indexOf(articleUrlEndIndex)
					- articleUrlEndOffset;

			System.out.println("articleUrlStartIndex: " + s);
			System.out.println("articleUrlEndIndex: " + e);
			if (s > e) {
				continue;
			}

			String articleUrl = articleUrlTitle.substring(s, e);

			System.out.println(articleUrl);
			int ss = articleUrlTitle.indexOf(articleTitleStartIndex)
					+ articleTitleStartOffset;
			int ee = articleUrlTitle.indexOf(articleTitleEndIndex)
					- articleTitleEndOffset;

			if (ss > ee) {
				continue;
			}
			String articleTitle = articleUrlTitle.substring(ss, ee);

			System.out.println(articleTitle);
			// [0] url [1] title
			System.out.println(articleUrl + " " + articleTitle);
			articleUrlsTitles.add(new String[] { articleUrl, articleTitle });
		}

		return articleUrlsTitles;
	}

	/**
	 * 
	 * @param url
	 * @param start
	 * @param end
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String getArticleContentUseHttpGet(String url, String start,
			String end) throws ClientProtocolException, IOException {
		String html = Tools.httpGet(url);
		String content = "";

		if (html != null) {
			int startIndex = html.indexOf(start);
			int endIndex = html.indexOf(end);

			System.out.println(startIndex);
			System.out.println(endIndex);

			if (startIndex < endIndex) {
				content = html.substring(startIndex, endIndex);
			}

		}
		return content;

	}

	/**
	 * 
	 * @param url
	 * @param start
	 * @param end
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String getArticleContentUseDriverGet(String url,
			String start, String end) throws ClientProtocolException,
			IOException {
		String html = Tools.driverGet(url);
		// System.out.println(html);
		String content = "";
		if (html != null) {
			int startIndex = html.indexOf(start);
			int endIndex = html.indexOf(end);

			System.out.println(startIndex);
			System.out.println(endIndex);

			if (startIndex < endIndex) {
				content = html.substring(startIndex, endIndex);
			}

		}
		return content;
	}

}
