
	        <div class="content">
	            <div class="container-fluid">
	                <div class="row">
	                    <div class="col-md-12">
	                        <div class="card">
	                            <div class="card-header" data-background-color="purple">
	                                <h4 class="title">Your Locations</h4>
	                            </div>
	                            <div class="card-content table-responsive">
	                                <table class="table">
	                                    <thead class="text-primary">
	                                    	<th>Name</th>
	                                    	<th>Type</th>
																				<th>Options</th>
	                                    </thead>
	                                    <tbody>
																				<?php foreach($centreList as $centre): ?>
																					<tr>
																						<td><?= $centre->getName()?></td>
																						<td><?= $centre->getCType()?></td>										
																							<td>
																								<button type="button" class="btn btn-danger" id="<?=$centre->getcID()?>">Remove</button>
																							</td>
																					</tr>
																				<?php endforeach; ?>
	                                    </tbody>
	                                </table>
	                            </div>
	                        </div>
	                    </div>
  								</div>
	            </div>
	        </div>
	    </div>
	</div>
