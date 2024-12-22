package com.bankapi.bankapiprototype

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.bankapi.bankapiprototype", "com.bankapi.bankapiprototype.controller",
		"com.bankapi.bankapiprototype.entity", "com.bankapi.bankapiprototype.repository", "com.bankapi.bankapiprototypeservice"])
class BankapiprototypeApplication

fun main(args: Array<String>) {
	runApplication<BankapiprototypeApplication>(*args)
}
