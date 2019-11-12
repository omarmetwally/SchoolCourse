using System;
using System.Collections.Generic;

namespace SchoolProject_Api.Models
{
    public partial class User
    {
        public Guid Uid { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }
        public byte[] Password { get; set; }
        public byte[] PasswordSalt { get; set; }
        public bool Gender { get; set; }
        public string Image { get; set; }
        public bool ActivationStatus { get; set; }
        public string ForgetPasswordKey { get; set; }
        public DateTime RegistrationData { get; set; }
        public int? UserTypeId { get; set; }
        public string City { get; set; }
        public string Region { get; set; }

        public Center Center { get; set; }
        public Student Student { get; set; }
        public Teacher Teacher { get; set; }
    }
}
