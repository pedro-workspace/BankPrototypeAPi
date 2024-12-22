package com.bankapi.bankapiprototype.exception

import java.time.LocalDateTime

class ExceptionDetail(
    val title:String,val timeStamp: LocalDateTime = LocalDateTime.now(),
    val status:Int,
    val exceptionName:String = "",
    val details:MutableMap<String, String?> = mutableMapOf(Pair("", "")) // a chave para o nome da exceção e o valor para mais detalhes, conforme regra de negócio
)