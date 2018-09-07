package com.uhope.template.web;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * {@link com.uhope.template.web.TemplateController} Tester.
 *
 * @author xiepuyao
 * @version 1.0
 * @since <pre>09/04/2018</pre>
 */
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.uhope")
@SpringBootTest(classes = TemplateControllerTest.class)
public class TemplateControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void before() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: add(Template template)
     */
    @Test
    public void testAdd() throws Exception {

    }

    /**
     * Method: delete(@RequestParam String id)
     */
    @Test
    public void testDelete() throws Exception {

    }

    /**
     * Method: update(Template template)
     */
    @Test
    public void testUpdate() throws Exception {

    }

    /**
     * Method: detail(@RequestParam String id)
     */
    @Test
    public void testDetail() throws Exception {

    }

    /**
     * Method: list(@RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "10") Integer pageSize)
     */
    @Test
    public void testList() throws Exception {
        String pageNumber = "1";
        String pageSize = "5";
        String result =
                mockMvc.perform(MockMvcRequestBuilders.get("/v1/template/list")
                        .param("pageNumber", pageNumber)
                        .param("pageSize", pageSize)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.resCode").value("1"))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.data.pageSize").value(pageSize))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.data.pageNum").value(1))
                        .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }


}
