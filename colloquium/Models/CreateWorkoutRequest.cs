using System.ComponentModel.DataAnnotations;

namespace colloquium.Models
{
    public class CreateWorkoutRequest
    {
        [Required]
        public string Name { get; set; }
        [Required]
        public string Description { get; set; }
        [Required]
        public string Instructor { get; set; }
        [Required]
        public int Duration { get; set; }
    }
}
