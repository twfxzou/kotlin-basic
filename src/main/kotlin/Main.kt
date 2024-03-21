
import com.thoughtworks.kotlin_basic.api.ApiClient
import com.thoughtworks.kotlin_basic.service.ProductService
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val productService = ProductService(ApiClient.productApiService)
    println(String.format("%-10s %-35s %-10s %-10s %-10s", "SKU", "名称", "价格", "库存量", "图片地址"))
    productService.getProducts().forEach { println(String.format("%-10s %-35s %-10s %-10s %-10s", it.sku, it.name, it.getRealPrice(), it.getInventoryQuantity(), it.image)) }
}