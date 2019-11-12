using System;
using System.Collections.Generic;

namespace SchoolProject_Api.Models
{
    public partial class Assignment
    {
        public int AssignmentId { get; set; }
        public int ClassId { get; set; }
        public string PdfLink { get; set; }
        public string Note { get; set; }

        public Class Class { get; set; }
    }
}
