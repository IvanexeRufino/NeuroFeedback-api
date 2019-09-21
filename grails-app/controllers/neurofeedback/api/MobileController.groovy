package neurofeedback.api

class MobileController{
    def passwordEncoder

    def index() {       
        render "You should not be doing this"
    }

    def authMobile(){ 
    	def map
        def req = params.docNumber
        def pass = params.password
        //def req = "11111111"
        //def pass = "123456"
        def user = User.findByDocNumber(req)
        if (user != null) {
            if (passwordEncoder.matches(pass, user.password)) { //validates raw password against hashed
                map = user
                respond map, formats: ['json']
            }
            else{
                map = [status: 400, message: ["response":"Error Password"]]
                respond map, formats: ['json']
            }
        } else {
            map = [status: 400, message: ["response":"Error User"]]
            respond map, formats: ['json']
        }
    }


    def getTreatment(){
        def map
        def userId = params.userId
        def userTreatment = UserTreatment.findAllByUser(User.findById(userId)).stream().filter { userT ->
            !userT.finished
        }.map {
            ut -> ut.treatment.toJson()
        }.collect()
        map = [status: 200, message: ["response":"Ok","treatments":userTreatment]]
        respond map, formats: ['json']
    }
}


    