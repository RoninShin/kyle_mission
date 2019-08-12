package com.kyle.mission;

import java.util.List;

import javax.validation.Valid;

import com.kyle.mission.controller.TouristInfoRestAPIs;
import com.kyle.mission.message.request.RegionForm;
import com.kyle.mission.model.Region;
import com.kyle.mission.model.User;
import com.kyle.mission.repository.RegionRepository;
import com.kyle.mission.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthRestApiTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void region_save_test() {
        List<User> userList = userRepository.findAll();

        assertThat(userList.size(), not(0));
    }

	// @Autowired
    // private MockMvc mvc;
    // @MockBean
    // private BookService bookService;
    // @Test
    // public void getBook_test() throws Exception {
    //     //given
    //     final Book book = new Book(1L, "title", 1000D);
    //     given(bookService.getBook()).willReturn(book);
    //     //when
    //     final ResultActions actions = mvc.perform(get("/books/{id}", 1L)
    //             .contentType(MediaType.APPLICATION_JSON_UTF8))
    //             .andDo(print());
    //     //then
    //     actions
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("id").value(1L))
    //             .andExpect(jsonPath("title").value("title"))
    //             .andExpect(jsonPath("price").value(1000D))
    //     ;
	// }
	// @Mock
    // private UserService userService;

    // @InjectMocks
    // private UserController userController;

    // @Test
    // public void issueTokenApiIntegrateTest() throws Exception {
    //     this.mockMvc.perform(get("/v1/issue-token"))
    //             .andDo(print())
    //             .andExpect(status().is(HttpStatus.OK.value()))
    //             .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
    //             .andExpect(jsonPath("$.result.token").exists())
    //             .andExpect(jsonPath("$.result.created_at").exists());
    // }

    // @Test
    // public void issueTokenApiUnitTest() {
    //     MockitoAnnotations.initMocks(this);
    //     LocalDateTime testTime = LocalDateTime.now();
    //     givenUserService(testTime);

    //     ApiResponseModel<AccessToken> result = userController.issueToken();

    //     Assert.assertEquals(result.getCode(), HttpStatus.OK.value());
    //     Assert.assertEquals(result.getMsg(), HttpStatus.OK.getReasonPhrase());
    //     Assert.assertEquals(result.getResult().getToken(), "this is sample access-token");
    //     Assert.assertEquals(result.getResult().getCreatedAt(), testTime);
    // }

    // private void givenUserService(LocalDateTime createdAt) {
    //     User mockUser = new User();
    //     mockUser.setAccessToken(new AccessToken("this is sample access-token", createdAt));
    //     when(userService.registerUser()).thenReturn(mockUser);
    // }
}