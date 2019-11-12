using System;
using System.Collections.Generic;

namespace SchoolProject_Api.Models
{
    public partial class Exam
    {
        public int ExamId { get; set; }
        public string ExamPdf { get; set; }
        public string SolutionPdf { get; set; }
        public DateTime? ExamDate { get; set; }
        public int? ClassId { get; set; }

        public Class Class { get; set; }
    }
}
