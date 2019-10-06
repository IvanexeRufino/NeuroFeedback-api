package neurofeedback.api

class MobileController{
    def passwordEncoder

    def index() {       
        render "You should not be doing this"
    }

    def authMobile(){
        def map

        def userJSON = request.JSON
        def docNumber = userJSON.docNumber
        def pass = userJSON.password

        def user = User.findByDocNumber(docNumber)
        if (user != null) {
            if (passwordEncoder.matches(pass, user.password)) {
                map = [status: 200, message: "Succesfuly Logged in"]

            }
            else{
                map = [status: 400, message: "Error Password"]
            }
        } else {
            map = [status: 400, message: "Error User"]
        }

        respond map, formats: ['json']
    }


    def getTreatment(){
        def map
        def userId = 4// params.userId
        def userTreatment = UserTreatment.findAllByUser(User.findById(userId)).stream().filter { userT ->
            userT.status == "Pending"
        }.map {
            ut -> ut.toJson()
        }.collect()
        map = [status: 200, message: userTreatment]
        respond map, formats: ['json']
    }
}


    