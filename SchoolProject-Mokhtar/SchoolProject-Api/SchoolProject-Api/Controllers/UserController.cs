using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Security.Claims;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using SchoolProject_Api.DTO;
using SchoolProject_Api.Models;
using SchoolProject_Api.Utilities;

namespace SchoolProject_Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        private readonly SchoolProjectDBContext context;
        private readonly IConfiguration configuration;
        public UserController(SchoolProjectDBContext _context, IConfiguration _configuration)
        {
            context = _context;
            configuration = _configuration;
        }

        [HttpPost, Route("registrationStudent")]

        public IActionResult registrationStudent([FromBody] UserDTO userDTO)
        {
            try
            {
                userDTO.UserTypeId = 2;
                userDTO.uid = context.User.Add(userDTO.ToUser()).Entity.Uid;

                context.Student.Add(userDTO.toStudent());

                context.SaveChanges();

                return Ok("Done");
            }
            catch (Exception ex)
            {
                return BadRequest("Email Exists");
            }
        }

        [HttpPost, Route("registrationTeacher")]

        public IActionResult registrationTeacher([FromBody] UserDTO userDTO)
        {
            try
            {
                userDTO.UserTypeId = 1;

                userDTO.uid = context.User.Add(userDTO.ToUser()).Entity.Uid;

                context.Teacher.Add(userDTO.toTeacher());
                context.SaveChanges();

                return Ok("Done");
            }
            catch (Exception ex)
            {
                return BadRequest("Email Exists");
            }
        }
        [HttpPost, Route("registrationCenter")]

        public IActionResult registrationCenter([FromBody] UserDTO userDTO)
        {
            try
            {
                userDTO.UserTypeId = 3;
                userDTO.uid = context.User.Add(userDTO.ToUser()).Entity.Uid;

                context.Center.Add(userDTO.toCenter());

                context.SaveChanges();

                return Ok("Done");
            }
            catch (Exception ex)
            {
                return BadRequest(ex);
            }
        }


        [HttpPost, Route("login")]
        public IActionResult login([FromBody] LoginDTO loginDTO)
        {
            try
            {
                User user = context.User.SingleOrDefault(x => x.Email == loginDTO.Email);
                if (UserService.VerifyPasswordHash(loginDTO.Password, user.Password, user.PasswordSalt))
                {
                    string tk = UserService.authenticate(user.Uid, user.UserTypeId == 1 ? "Teacher" : "Student");


                    //return Ok(tk);
                    return StatusCode(200, "{ \"token\": \""+tk+"\"}");

                }

                return NotFound("Wrong Email or Password");

            }
            catch (Exception ex)
            {
                return BadRequest(ex);
            }
        }

        [HttpPut, Route("update")]
        public IActionResult update([FromHeader] string authorization, [FromBody] UserDTO userDTO)
        {
            try {
                var id = UserService.getUID(authorization);
                var user = context.User.First(x => x.Uid == id);

                if (user.UserTypeId == 1)
                {
                    context.Teacher.Update(userDTO.toTeacher());
                }

                else if (user.UserTypeId == 2)
                {
                    context.Teacher.Update(userDTO.toTeacher());
                }
                else if (user.UserTypeId == 3)
                {
                    context.Teacher.Update(userDTO.toTeacher());
                }
                context.User.Update(userDTO.ToUser());
                context.SaveChanges();
                return Ok("success");
            } catch (Exception ex)
            {
                return BadRequest(ex);
            }
        }
        [HttpPost, Route("RetrieveProfile")]
        public IActionResult RetrieveProfile([FromHeader] string authorization)
        {
            try
            {
                var id = UserService.getUID(authorization);
                var user = context.User.First(x => x.Uid == id);
                var generic = "";
                if (user.UserTypeId == 1)
                {
                    generic = $",{context.Teacher.Find(user.Uid).ToString()}";
                }

                else if (user.UserTypeId == 2)
                {
                    generic = $",{context.Student.Find(user.Uid).ToString()}";
                }
                else if (user.UserTypeId == 3)
                {
                    generic = $",{context.Center.Find(user.Uid).ToString()}";
                }
                return Ok("{"+
                    "\"User\":{" + user + "" + generic
                +"}}");
            }
            catch (Exception ex)
            {
                return BadRequest(ex);
            }
        }

        //Delete user profile permanently 
        //thinking about not deleting rather than a bit to check if account is working
        [HttpDelete]
        [Route("Delete")]
        public IActionResult Delete([FromHeader] string Authorization)
        {
            var id = UserService.getUID(Authorization);
            context.Student.Remove(context.Student.SingleOrDefault(x => x.Sid == id));
            context.User.Remove(context.User.SingleOrDefault(x => x.Uid == id));
            context.SaveChanges();
            return Ok("deleted success");
        }


        //uploading Image section, use Form to test it using POSTMAN
        [Route("imageUploading")]
        [HttpPost]
        public async Task<IActionResult> imageUploadingAsync([FromHeader] string Authorization, IFormFile file)
        {
            var filePath = Path.Combine(Directory.GetCurrentDirectory() + "/wwwroot/Images", file.FileName);

            using (var stream = new FileStream(filePath, FileMode.Create))
            {
                await file.CopyToAsync(stream);

            }
            var id = UserService.getUID(Authorization);
            var userMatching = context.User.SingleOrDefault(x => x.Uid == id);
            userMatching.Image = filePath;
            await context.SaveChangesAsync();
            return Ok("Uploaded Successfully");

        }

        //Download image section, use Form to test it using POSTMAN
        [HttpPost, Route("imageDownloading")]
        public async Task<IActionResult> imageDownloadingAsync([FromHeader] string Authorization)
        {

            var id = UserService.getUID(Authorization);
            var userMatching = context.User.SingleOrDefault(x => x.Uid == id);
            var image = System.IO.File.OpenRead(userMatching.Image);
            return File(image, "image/jpeg");
        }


        //if user forget his password, please to test the email enter your email and password if it didn't work
        [HttpPost]
        [Route("ForgetPassword")]
        public IActionResult forgetPassword([FromBody] String Email)
        {
            var user = context.User.SingleOrDefault(x => x.Email == Email);
            if (user == null) return NotFound("there's no account with this email");
            string key = UserService.RandomString(8);
            user.ForgetPasswordKey = key;
            using (var message = new MailMessage())
            {

                message.To.Add(new MailAddress(user.Email, "To Email"));
                message.From = new MailAddress(configuration["Gmail_Email"], "From Email");
                message.Subject = "NO REPLY";
                message.Body = $"your key: {key}";
                message.IsBodyHtml = true;
                using (var client = new SmtpClient("smtp.gmail.com"))
                {
                    client.Port = 587;
                    client.Credentials = new NetworkCredential(configuration["Gmail_Email"], configuration["Gmail_Password"]);
                    client.EnableSsl = true;
                    client.Send(message);
                    context.SaveChanges();
                    return Ok("Check your email for key");
                }
            }
        }

        [HttpPost, Route("checkKeyCorrect")]
        public IActionResult checkKeyCorrect([FromBody] ForgetPasswordParameter userfpp)
        {
            var user = context.User.SingleOrDefault(x => x.Email == userfpp.Email);
            if (user == null) return NotFound("user not found");
            if (user.ForgetPasswordKey != userfpp.key) return BadRequest("key isn't correct!");
            byte[] psdhash, psdsalt;

            UserService.CreatePasswordHash(userfpp.password, out psdhash, out psdsalt);
            user.Password = psdhash;
            user.PasswordSalt = psdsalt;

            context.User.Update(user);
            context.SaveChanges();
            return Ok("Password Changed!");
        }

    }
}