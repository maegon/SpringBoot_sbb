package com.sbs.sbb;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component
// 컴포넌트 이름 CommonUtil
// 컴포넌트를 타임리프에서 접근 할 땐
// @commonUtil 로 앞 글자를 소문자로 변경하여 작성
public class CommonUtil {
    public String markdown(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
}
