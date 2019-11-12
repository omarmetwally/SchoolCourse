using SchoolProject_Api.Models;
using SchoolProject_Api.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SchoolProject_Api.DTO
{
    public class UserDTO
    {
        public Guid uid { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Password { get; set; }
        public string Email { get; set; }
        public bool Gender { get; set; }
        public string Image { get; set; }
        public bool ActivationStatus { get; set; }
        public string ForgetPasswordKey { get; set; }
        public string Region { get; set; }
        public string City { get; set; }
        public int? MajorId { get; set; }
        public decimal Rank { get; set; }
        public string Description { get; set; }

        public int? UserTypeId { get; set; }
        public  User ToUser()
        {
            byte[] psdhash, psdsalt;

            UserService.CreatePasswordHash(this.Password, out psdhash, out psdsalt);

            return new User
            {
                FirstName = this.FirstName,
                LastName = this.LastName,
                Email = this.Email,
                Password = psdhash,
                PasswordSalt = psdsalt,
                ActivationStatus = false,
                City = this.City,
                Region = this.Region,
                Gender = this.Gender,
                RegistrationData = Convert.ToDateTime(DateTime.Now.ToString("MM/dd/yyyy hh:mm:ss tt")),
                UserTypeId = this.UserTypeId
            };
        }
        public  Student toStudent()
        {
            return new Student
            {
                Sid = this.uid,
                UserTypeId = 2,
            };
        }
        public  Teacher toTeacher()
        {
            return new Teacher
            {
                Tid = this.uid,
                Rank = this.Rank,
                Description = this.Description,
                UserTypeId = 1
            };
        }
        public  Center toCenter()
        {
            return new Center
            {
                Cid = this.uid,
                Rate = (decimal)0.0,
                Description = this.Description,
                UserTypeId = 3
            };
        }

    }
}
