
import com.thoughtworks.kotlin_basic.api.ApiClient
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking


fun main() = runBlocking<Unit> {
    // 启动并发的协程以验证主线程并未阻塞
    val products = async { ApiClient.apiService.queryProducts() }
    val inventories = async { ApiClient.apiService.queryInventories() }

    println("products is: ${products.await()}")
    println("inventories is: ${inventories.await()}")
}
//sampleEnd