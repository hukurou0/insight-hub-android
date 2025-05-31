# BookAnalysisApi

All URIs are relative to *https://mny9hjtbri.ap-northeast-1.awsapprunner.com*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**analyzeBook**](BookAnalysisApi.md#analyzeBook) | **POST** /api/book-analysis/analyze | 本の分析を実行する |


<a id="analyzeBook"></a>
# **analyzeBook**
> BookAnalysisResult analyzeBook(analyzeBookRequest)

本の分析を実行する

本の表紙の画像を受け取り、本のタイトル、著者名、カテゴリーを返却します

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = BookAnalysisApi()
val analyzeBookRequest : AnalyzeBookRequest =  // AnalyzeBookRequest | 
try {
    val result : BookAnalysisResult = apiInstance.analyzeBook(analyzeBookRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling BookAnalysisApi#analyzeBook")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling BookAnalysisApi#analyzeBook")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **analyzeBookRequest** | [**AnalyzeBookRequest**](AnalyzeBookRequest.md)|  | |

### Return type

[**BookAnalysisResult**](BookAnalysisResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

