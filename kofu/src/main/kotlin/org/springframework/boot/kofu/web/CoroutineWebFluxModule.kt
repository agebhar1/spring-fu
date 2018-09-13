package org.springframework.boot.kofu.web

import org.springframework.boot.kofu.AbstractModule
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.registerBean
import org.springframework.fu.web.function.client.CoroutinesWebClient
import org.springframework.fu.web.function.server.CoroutinesRouterFunctionDsl

class CoroutinesWebFluxClientModule(private val clientModule: WebFluxClientModule) : AbstractModule() {
	override fun registerBeans(context: GenericApplicationContext) {
		context.registerBean {
			if (clientModule.baseUrl != null) {
				CoroutinesWebClient.create(clientModule.baseUrl)
			} else {
				CoroutinesWebClient.create()
			}
		}
	}
}

fun WebFluxClientModule.coroutines()  {
	initializers.add(CoroutinesWebFluxClientModule(this))
}

fun WebFluxServerModule.coRouter(routes: (CoroutinesRouterFunctionDsl.() -> Unit)) {
	this.include { CoroutinesRouterFunctionDsl(routes).invoke() }
}
