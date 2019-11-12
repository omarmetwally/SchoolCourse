using System;
using System.Collections.Generic;

namespace SchoolProject_Api.Models
{
    public partial class TeachingSubjectEs
    {
        public Guid Tid { get; set; }
        public int Es { get; set; }
        public Guid? Cid { get; set; }

        public Center C { get; set; }
        public EduactionSubject EsNavigation { get; set; }
        public Teacher T { get; set; }
    }
}
