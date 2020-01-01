package com.atuigu.elasticsearch;

import com.atuigu.elasticsearch.bean.Article;
import com.atuigu.elasticsearch.bean.Book;
import com.atuigu.elasticsearch.repository.BookRepositroy;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot03ElasticsearchApplicationTests {

    @Autowired
    JestClient jestClient;

    @Test
    public void contextLoads() {
        // 1、给ES中索引（保存）一个文档
        Article article = new Article();
        article.setId(1);
        article.setTitle("庆祝国庆");
        article.setAuthor("张三");
        article.setContent("国庆节真快乐！");

        //构建一个索引功能
        Index index = new Index.Builder(article).index("atguigu").type("news").build();

        try {
            //执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void search(){
        //查询表达式
        String json = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"快乐\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        //构建搜索功能
        Search search = new Search.Builder(json).addIndex("atguigu").addType("news").build();

        //执行
        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // springData elasticSearch

    @Autowired
    BookRepositroy bookRepositroy;

    @Test
    public void test01(){
//        Book book = new Book(); //在book类上标注注解指定 index 和 type
//        book.setId(1);
//        book.setBookName("西游记");
//        book.setAuthor("吴承恩");
//        bookRepositroy.index(book);

        List<Book> books = bookRepositroy.findByBookNameLike("游");
        for (Book b : books){
            System.out.println(b);
        }
    }

}
