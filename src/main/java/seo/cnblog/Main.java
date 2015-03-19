/**
 * 
 */
package seo.cnblog;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.xmlrpc.XmlRpcException;

import seo.common.Const;
import seo.common.Param;

/**
 * @author jack
 *
 */
public class Main {
	public final static String BLOG_CAT_PREFIX = Messages
			.getString("cat.prefix");

	/**
	 * @param args
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws InterruptedException
	 * @throws XmlRpcException
	 */
	public static void main(String[] args) throws ClientProtocolException,
			IOException, XmlRpcException, InterruptedException {
		Param param = new Param(articleUrlRegex, articleUrlStartIndex,
				articleUrlEndIndex, articleUrlStartIndexOffset,
				articleUrlEndIndexOffset, articleTitleRegex,
				articleTitleStartIndex, articleTitleEndIndex,
				articleTitletartIndexOffset, articleTitleEndIndexOffset,
				articleContentStart, articleContentEnd, xmlrpcApi, username,
				password, Const.BLOG_TITLE_SEO, Const.BLOG_FOOTER_SEO,
				BLOG_CAT_PREFIX);

		String url = "http://www.cnblogs.com/#p";
		String urlPick = "http://www.cnblogs.com/pick/#p";
		String urlCandidate = "http://www.cnblogs.com/candidate/#p";

		seo.common.BlogSender.sendUseDriverGet(url, 154, 200, param);
		seo.common.BlogSender.sendUseDriverGet(urlPick, 1, 72, param);
		seo.common.BlogSender.sendUseDriverGet(urlCandidate, 1, 200, param);

	}

	/**
	 * wordpress http://localhost/ols/xmlrpc.php
	 */
	public final static String xmlrpcApi = Messages.getString("Main.xmlrpcApi"); //$NON-NLS-1$
	public final static String username = Messages
			.getString("Main.xmlrpcUserName"); //$NON-NLS-1$
	public final static String password = Messages
			.getString("Main.xmlRpcPassword"); //$NON-NLS-1$

	public static final String BLOG_SEO_FOOTER_KEYWORDS = Messages
			.getString("Main.BlogSEOFooter"); //$NON-NLS-1$
	public static final String BLOG_SEO_TITLE_KEYWORDS = Messages
			.getString("Main.BlogSEOTitle"); //$NON-NLS-1$

	/**
	 * article Url
	 */
	public static final String articleUrlRegex = Messages
			.getString("Main.articleUrlRegex"); //$NON-NLS-1$
	public static final String articleUrlStartIndex = Messages
			.getString("Main.articleUrlStartIndex"); //$NON-NLS-1$
	public static final String articleUrlEndIndex = Messages
			.getString("Main.articleUrlEndIndex"); //$NON-NLS-1$
	public static final int articleUrlStartIndexOffset = 0;
	public static final int articleUrlEndIndexOffset = 2;
	/**
	 * article Title
	 */
	public static final String articleTitleRegex = Messages
			.getString("Main.articleTitleRegex"); //$NON-NLS-1$
	public static final String articleTitleStartIndex = Messages
			.getString("Main.articleTitleStartIndex"); //$NON-NLS-1$
	public static final String articleTitleEndIndex = Messages
			.getString("Main.articleTitleEndIndex"); //$NON-NLS-1$
	public static final int articleTitletartIndexOffset = Messages.getString(
			"Main.articleTitletartIndexOffset").length(); //$NON-NLS-1$
	public static final int articleTitleEndIndexOffset = 0;

	/**
	 * article Content
	 */
	public static final String articleContentStart = Messages
			.getString("Main.articleContentStart"); //$NON-NLS-1$
	public static final String articleContentEnd = Messages
			.getString("Main.articleContentEnd"); //$NON-NLS-1$

	/**
	 * ÿҳ�����б��url
	 */
	//	public final static String url = Messages.getString("Main.cnblogsPageUrl"); //$NON-NLS-1$
	// public final static String urlPick = Messages
	// .getString("Main.cnblogsPageUrlPick");
	// public final static String urlCandidate = Messages
	// .getString("Main.cnblogsPageUrlCandidate");
}
