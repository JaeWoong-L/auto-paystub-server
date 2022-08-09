package com.jw.autopaystubserver.domain.company

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(CompanyController::class)
@ExtendWith(RestDocumentationExtension::class, SpringExtension::class)
@AutoConfigureRestDocs
class CompanyControllerTest() : CommonRestDocsEntity() {
    @MockBean
    lateinit var companyService: CompanyService

    @Test
    fun `company-post 문서`() {
        val company = Company(
            name = "회사",
            businessNumber = "123-12-12345",
            representative = "대표",
            logoImageUrl = "이미지url",
            message = "귀하의 노고에 감사드립니다."
        )

        val request = CompanyDto.Request(
            name = "회사",
            businessNumber = "123-12-12345",
            representative = "대표",
            logoImageUrl = "이미지url",
            message = "귀하의 노고에 감사드립니다."
        )

        given(companyService.register(request)).willReturn(company)

        val result = mockMvc.perform(post("/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request))
            .accept(MediaType.APPLICATION_JSON)
        )

        result.andExpect(status().isOk)
            .andDo(
                document(
                    "company/company-post",
                    getDocumentationRequest(),
                    getDocumentationResponse(),
                    requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("businessNumber").type(JsonFieldType.STRING).description("사업자 번호"),
                        fieldWithPath("representative").type(JsonFieldType.STRING).description("대표자명"),
                        fieldWithPath("logoImageUrl").type(JsonFieldType.STRING).description("회사로고"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("급여명세서에 넣을 메시지")
                    ),
                    responseFields(
                        fieldWithPath("data.company.id").type(JsonFieldType.NUMBER).description("id"),
                        fieldWithPath("data.company.name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("data.company.businessNumber").type(JsonFieldType.STRING).description("사업자 번호"),
                        fieldWithPath("data.company.representative").type(JsonFieldType.STRING).description("대표자명"),
                        fieldWithPath("data.company.logoImageUrl").type(JsonFieldType.STRING).description("회사로고"),
                        fieldWithPath("data.company.message").type(JsonFieldType.STRING).description("급여명세서에 넣을 메시지"),
                        fieldWithPath("data.company.createdAt").type(JsonFieldType.VARIES).description("최초 생성 일자"),
                        fieldWithPath("data.company.updatedAt").type(JsonFieldType.VARIES).description("최종 수정 일자")
                    )
                )
            )
    }

    @Test
    fun `company-get 문서`() {
        val company1 = Company(
            name = "회사1",
            businessNumber = "123-12-12345",
            representative = "대표1",
            logoImageUrl = "이미지1",
            message = "귀하의 노고에 감사드립니다."
        )

        val company2 = Company(
            name = "회사2",
            businessNumber = "321-21-54321",
            representative = "대표2",
            logoImageUrl = "이미지2",
            message = "귀하의 노고에 감사드립니다."
        )

        val response = listOf(company1, company2)

        given(companyService.findCompanies()).willReturn(response)

        val result = mockMvc.perform(get("/companies")
            .accept(MediaType.APPLICATION_JSON)
        )

        result.andExpect(status().isOk)
            .andDo(
                document(
                    "company/company-get-all",
                    getDocumentationRequest(),
                    getDocumentationResponse(),
                    responseFields(
                        fieldWithPath("data.companies.[].id").type(JsonFieldType.NUMBER).description("id"),
                        fieldWithPath("data.companies.[].name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("data.companies.[].businessNumber").type(JsonFieldType.STRING).description("사업자 번호"),
                        fieldWithPath("data.companies.[].representative").type(JsonFieldType.STRING).description("대표자명"),
                            fieldWithPath("data.companies.[].logoImageUrl").type(JsonFieldType.STRING).description("회사로고"),
                            fieldWithPath("data.companies.[].message").type(JsonFieldType.STRING).description("급여명세서에 넣을 메시지"),
                        fieldWithPath("data.companies.[].createdAt").type(JsonFieldType.VARIES).description("최초 생성 일자"),
                        fieldWithPath("data.companies.[].updatedAt").type(JsonFieldType.VARIES).description("최종 수정 일자")
                    )
                )
            )
    }

    @Test
    fun `company-delete 문서`() {
        val result = mockMvc.perform(RestDocumentationRequestBuilders.delete("/companies/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        )

        result.andExpect(status().isNoContent)
            .andDo(
                document(
                    "company/company-delete",
                    getDocumentationRequest(),
                    getDocumentationResponse(),
                    pathParameters(
                        parameterWithName("id").description("company id")
                    )
                )
            )
    }
}