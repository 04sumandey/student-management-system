<html>
<head>
<meta charset="ISO-8859-1">
<title>User Details</title>
<script>
        function validateForm() {
            var id = document.getElementById("id").value;
            var name = document.getElementById("name").value;
            var phNo = document.getElementById("phNo").value;
            var email = document.getElementById("email").value;

            if (id === "" || name === "" || phNo === "" || email === "") {
                alert("All fields are required");
                return false;
            }
var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(email)) {
                alert("Please enter a valid email address");
                return false;
            }

            return true;
        }
    </script>
</head>
<body >
<h2>Fill in the Data</h2>
<form action="FormData" method="post">
        <table>
            <tr>
                            <td>Id:</td>
                            <td><input type="text" name="id" /></td>
                        </tr>

            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" /></td>
            </tr>
            <tr>
                        <td>Phone Number:</td>
                        <td><input type="text" name="phNo" /></td>
                        </tr>
               <tr>
               <td>Email id:</td>
               <td><input type="text" name="email" /></td>

               </tr>
               <tr>
               <td><input type="submit" name="submit" /></td>
               </tr>
               </table>
               </form>
               </body>
               </html>