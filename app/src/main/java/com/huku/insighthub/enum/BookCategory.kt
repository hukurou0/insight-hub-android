package com.huku.insighthub.enum

enum class BookCategory(
    val value: String,
) {
    BUSINESS("ビジネス"),
    TECHNOLOGY("技術書"),
    NOVEL("小説"),
    SELF_HELP("自己啓発"),
    HISTORY("歴史"),
    SCIENCE("科学"),
    OTHER("その他"),
    ;

    companion object {
        fun values() = enumValues<BookCategory>()
    }
}
