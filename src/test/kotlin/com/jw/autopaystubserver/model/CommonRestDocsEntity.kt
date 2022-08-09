package com.jw.autopaystubserver.model

import com.fasterxml.jackson.databind.ObjectMapper
import com.jw.autopaystubserver.JWTTokenProvider
import com.jw.autopaystubserver.domain.company.Company
import com.jw.autopaystubserver.domain.worker.Worker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.test.web.servlet.MockMvc

open class CommonRestDocsEntity() {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    // @EnableWebSecurity에 필요한 Bean을 mocking하기 위함.
    @MockBean
    lateinit var jwtTokenProvider: JWTTokenProvider

    // @EnableJpaAuditing에 필요한 Bean을 mocking하기 위함.
    @MockBean
    lateinit var jpaMetaModelMappingContest: JpaMetamodelMappingContext

    companion object {
        val company = Company(
            name = "회사",
            businessNumber = "123-12-12345",
            representative = "대표",
            logoImageUrl = "이미지url",
            message = "귀하의 노고에 감사드립니다."
        )

        val worker = Worker(
            name = "이재웅",
            email = "example@gmail.com",
            mobilePhone = "010-1234-5678",
            gender = "남",
            birth = "941012",
            company = company
        )
    }
}