package neurofeedback.api

class Point {

    def x
    def y

    Point(axis, value) {
        this.x = axis
        this.y = value
    }
    def toJson(){
    	//["x":x,"y":y]
    	return "{\"x\":"+x+",\"y\":"+y+"}"
    }
}
