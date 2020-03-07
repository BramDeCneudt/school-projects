using Model.AI;
using Bindings;
using Model.Forces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Mathematics;

namespace Model.Species
{
    public class ControllerSpecies : BoidSpecies
    {

        public static RangedDoubleParameter X = new RangedDoubleParameter("X", defaultValue: 0, minimum: -1000, maximum: 1000);
        public static RangedDoubleParameter Y = new RangedDoubleParameter("Y", defaultValue: 0, minimum: -1000, maximum: 1000);


        public ControllerSpecies(World world)
            : base(world, "controller")
        {
            Bindings
                .Initialize(X)
                .Initialize(Y);
        }

        internal override IArtificialIntelligence CreateAI(Boid boid)
        {
            return new ArtificialIntelligence(World, boid);
        }

        private class ArtificialIntelligence : Model.AI.ArtificialIntelligence
        {

            public ArtificialIntelligence(World world, Boid self)
                : base(world, self)
            {
            }

            public override Vector2D ComputeAcceleration()
            {
                var total = new Vector2D(0, 0);

                double XValue = this.self.Bindings.Read(X).Value;
                double YValue = this.self.Bindings.Read(Y).Value;

                total += new Vector2D(XValue, YValue);

                //this.self.Bindings.Read(X).Value = 0;
                //this.self.Bindings.Read(Y).Value = 0;

                return total;
            }
        }
    }
}
