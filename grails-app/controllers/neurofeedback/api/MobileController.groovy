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
                map = [status:200, message:user]
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


    //Recibe un user_id y devuelve el array de todos sus tratamientos pendientes.
    def getTreatments(){
        def map
        def userId = params.userId
        def userTreatment = UserTreatment.findAllByUser(User.findById(userId)).stream().filter { userT ->
            userT.status == "Pending"
        }.map {
            ut -> ut.toJson()
        }.collect()
        map = [status: 200, message: userTreatment]
        respond map, formats: ['json']
    }

    //recibe un userTreatment_id y devuelve un unico tratamiento
    def getUserTreatment(){
        def map
        def userId = params.userTreatment_id
        def userTreatment = UserTreatment.findById(userId).toJson()
        map = [status: 200, message:userTreatment]
        respond map, formats: ['json']
    }
}


    