package com.bankapi.bankapiprototype.exceptions

class BussinessException(override val message:String?):Exception(message) {
    constructor(message:String, exceptionDetail: ExceptionDetail):this(message){
        println(exceptionDetail.toString())
    }
}