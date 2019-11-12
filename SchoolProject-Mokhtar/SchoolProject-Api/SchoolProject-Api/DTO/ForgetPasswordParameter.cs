using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SchoolProject_Api.DTO
{
    public class ForgetPasswordParameter
    {
        public string Email { set; get; }
        public string key { set; get; }
        public string password { set; get; }
    }
}
