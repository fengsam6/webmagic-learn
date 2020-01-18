package com.feng;

        import com.feng.util.HtmlUnitUtils;
        import com.gargoylesoftware.htmlunit.html.HtmlPage;
        import org.junit.Test;

public class HtmlUnitTest {
    @Test
    public void test() {
        String url = "https://list.iqiyi.com/www/1/-------------11-1-1-iqiyi--.html";
        HtmlPage htmlPage = HtmlUnitUtils.getHtmlPage(url);
        String content = HtmlUnitUtils.getHtmlPageResponse(url);
        System.out.print(content);
    }
}
