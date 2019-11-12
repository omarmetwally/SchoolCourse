using System;
using System.Collections.Generic;

namespace SchoolProject_Api.Models
{
    public partial class UserTypeId
    {
        public UserTypeId()
        {
            Center = new HashSet<Center>();
            Student = new HashSet<Student>();
            Teacher = new HashSet<Teacher>();
        }

        public int UserTypeId1 { get; set; }
        public string UserType { get; set; }

        public ICollection<Center> Center { get; set; }
        public ICollection<Student> Student { get; set; }
        public ICollection<Teacher> Teacher { get; set; }
    }
}
