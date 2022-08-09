package com.jw.autopaystubserver.domain.worker

import com.jw.autopaystubserver.model.CommonRestDocsEntity
import com.jw.autopaystubserver.util.ApiDocumentationUtil.getDocumentationRequest
import com.jw.autopaystubserver.util.ApiDocumentationUtil.getDocumentationResponse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(WorkerController::class)
@ExtendWith(RestDocumentationExtension::class, SpringExtension::class)
@AutoConfigureRestDocs
class WorkerControllerTest : CommonRestDocsEntity() {
    @MockBean
    lateinit var workerService: WorkerService

    @Test
    fun `worker-post 문서`() {
        val worker = CommonRestDocsEntity.worker

        val request = WorkerDto.Request(
            name = "회사",
            email = "example@gmail.com",
            mobilePhone = "010-1234-5678",
            gender = "남",
            birth = "941012",
            companyId = 1
        )

        given(workerService.register(request)).willReturn(worker)

        val result = mockMvc.perform(
            post("/workers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON)
        )

        result.andExpect(status().isOk)
            .andDo(
                document(
                    "worker/worker-post",
                    getDocumentationRequest(),
                    getDocumentationResponse(),
                    requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일 주소"),
                        fieldWithPath("mobilePhone").type(JsonFieldType.STRING).description("휴대폰 번호"),
                        fieldWithPath("gender").type(JsonFieldType.STRING).description("성별"),
                        fieldWithPath("birth").type(JsonFieldType.STRING).description("생년월일"),
                        fieldWithPath("companyId").type(JsonFieldType.NUMBER).description("회사 id")
                    ),
                    responseFields(
                        fieldWithPath("data.worker.id").type(JsonFieldType.NUMBER).description("id"),
                        fieldWithPath("data.worker.name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("data.worker.email").type(JsonFieldType.STRING).description("이메일 주소"),
                        fieldWithPath("data.worker.mobilePhone").type(JsonFieldType.STRING).description("휴대폰 번호"),
                        fieldWithPath("data.worker.gender").type(JsonFieldType.STRING).description("성별"),
                        fieldWithPath("data.worker.birth").type(JsonFieldType.STRING).description("생년월일"),
                        fieldWithPath("data.worker.createdAt").type(JsonFieldType.VARIES).description("최초 생성 일자"),
                        fieldWithPath("data.worker.updatedAt").type(JsonFieldType.VARIES).description("최종 수정 일자"),
                        subsectionWithPath("data.worker.company").type(JsonFieldType.OBJECT).description("소속회사")
                    )
                )
            )
    }

    @Test
    fun `worker-delete 문서`() {
        val result = mockMvc.perform(
            RestDocumentationRequestBuilders.delete("/workers/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )

        result.andExpect(status().isNoContent)
            .andDo(
                document(
                    "worker/worker-delete",
                    getDocumentationRequest(),
                    getDocumentationResponse(),
                    pathParameters(
                        parameterWithName("id").description("worker id")
                    )
                )
            )
    }
}