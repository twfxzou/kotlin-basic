
import com.thoughtworks.kotlin_basic.api.ApiClient
import com.thoughtworks.kotlin_basic.service.ProductService
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val productService = ProductService(ApiClient.productApiService)
    productService.getProducts().forEach { println(it) }
}