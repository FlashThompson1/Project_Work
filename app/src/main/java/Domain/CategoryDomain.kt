package Domain

class CategoryDomain {
    private var title: String? = null
    private var pic: String? = null



    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String){
        this.title = title;
    }

    fun getPic(): String? {
        return pic
    }
    fun setPic(pic: String){
        this.pic = pic;
    }

    constructor(title: String?, pic: String?) {
        this.title = title
        this.pic = pic
    }
}