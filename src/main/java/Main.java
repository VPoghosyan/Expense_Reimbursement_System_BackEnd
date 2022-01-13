

import io.javalin.Javalin;

import io.javalin.apibuilder.ApiBuilder;
import io.javalin.http.staticfiles.Location;


import controller.ContEmployee;

public class Main {

	public static void main(String[] args) {
		Javalin app = Javalin.create(ctx->{
			ctx.enableCorsForAllOrigins();
			
			
		}).start(7777);//creates a server and runs it on 7777
		//{"web", Location.CLASSPATH}
		//app.get("/", ctx->{ctx.result("Hello W");});//a handler: makes a request with 
		//the first arg, which is also the path, and returns the second arg. Basically
		//works like a rest api
		//Ultimately, the second arg is going to be the controller   
		
		app.get("/hello/{name}", ctx->{ctx.result("Hello Again "+ ctx.pathParam("name"));});//dynamic values

		
		
		//app.get("/cakes/{special}", ctx->{ctx.result(Cackes.getSpecialCake0(ctx.pathParam("special")));});
		//this is the long way of doing what is done in line /hello/{name}
		
		
		app.post("/login", ContEmployee::employeeLogIn);
		//use ContStudents::getStudents to connect to db using uName and uPass as arguments
		
		app.post("/updateRole", ContEmployee.roleUpdate);
		app.post("/newClaim", ContEmployee.newClaim);
		app.post("/getIndClaims", ContEmployee.getClaims);
		app.post("/claimDecision", ContEmployee.claimDecision);
		app.post("/getEmps", ContEmployee.getEmps);
		
	}

}


