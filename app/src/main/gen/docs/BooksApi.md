# BooksApi

All URIs are relative to *https://mny9hjtbri.ap-northeast-1.awsapprunner.com*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**createBook**](BooksApi.md#createBook) | **POST** /api/books | 本を登録する |
| [**uploadBookImage**](BooksApi.md#uploadBookImage) | **POST** /api/books/img-upload | 本の表紙画像をアップロード |


<a id="createBook"></a>
# **createBook**
> BookModel createBook(userId, bookCreate)

本を登録する

本の情報を登録します

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = BooksApi()
val userId : kotlin.String = userId_example // kotlin.String | ユーザーID
val bookCreate : BookCreate =  // BookCreate | 
try {
    val result : BookModel = apiInstance.createBook(userId, bookCreate)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling BooksApi#createBook")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling BooksApi#createBook")
    e.printStackTrace()
}
```

### Parameters
| **userId** | **kotlin.String**| ユーザーID | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **bookCreate** | [**BookCreate**](BookCreate.md)|  | |

### Return type

[**BookModel**](BookModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="uploadBookImage"></a>
# **uploadBookImage**
> UploadBookImage200Response uploadBookImage(file)

本の表紙画像をアップロード

本の表紙画像をSupabase Storageにアップロードします

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = BooksApi()
val file : java.io.File = BINARY_DATA_HERE // java.io.File | アップロードする画像ファイル
try {
    val result : UploadBookImage200Response = apiInstance.uploadBookImage(file)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling BooksApi#uploadBookImage")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling BooksApi#uploadBookImage")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **file** | **java.io.File**| アップロードする画像ファイル | [optional] |

### Return type

[**UploadBookImage200Response**](UploadBookImage200Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

