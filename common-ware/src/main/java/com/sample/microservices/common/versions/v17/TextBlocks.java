package com.sample.microservices.common.versions.v17;


/*
    we use Text Blocks to create a multi-line string.
    You can see that the indentation, new line etc. is preserved,
    and you don't need to manually escape newline characters with \n.
    This makes it much easier to write and maintain large blocks of text within your code.

    Text Blocks are particularly useful when working with SQL queries,
    JSON, HTML, XML, or any other text-based content that spans multiple lines.
    They help improve code readability and maintainability.

 */
public class TextBlocks {
    public static void main(String[] args) {
        String blockText = """
            This is a text block new feature in Java 17.
               It allows you to write multi-line strings
            without needing to escape newline characters.
            
            You can also preserve leading whitespace and new line\nfor formatting.
            \tIndented text is also preserved.
            """;

        System.out.println(blockText);
    }
}
