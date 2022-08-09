package com.jw.autopaystubserver.util

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris

object ApiDocumentationUtil {
    fun getDocumentationRequest(): OperationRequestPreprocessor {
        return Preprocessors.preprocessRequest(
//            modifyUris()
//                .scheme("http")
//                .host("ec2-54-180-106-221.ap-northeast-2.compute.amazonaws.com")
//                .port(8080),
            Preprocessors.prettyPrint()
        )
    }

    fun getDocumentationResponse(): OperationResponsePreprocessor {
        return Preprocessors.preprocessResponse(
            Preprocessors.prettyPrint()
        )
    }
}